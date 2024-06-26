package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CartDetailRepository cartDetailRepository,
            CartRepository cartRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Product createProduct(Product pr) {
        return this.productRepository.save(pr);
    }

    public Page<Product> fetchProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Optional<Product> fetchProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public Cart fetchCartByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void handleAddProductToCart(String email, Long productId, HttpSession session, long quantity) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            // check User đã có cart chưa
            if (cart == null) {
                // tạo cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);
                this.cartRepository.save(newCart);
                cart = newCart;
            }
            // save CartDetail
            Optional<Product> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                if (!this.cartDetailRepository.existsByCartAndProduct(cart, product)) {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(product);
                    cartDetail.setPrice(product.getPrice());
                    cartDetail.setQuantity(quantity);
                    this.cartDetailRepository.save(cartDetail);

                    // update cart sum
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    CartDetail newCartDetail = this.cartDetailRepository.findByCartAndProduct(cart, product);
                    newCartDetail.setQuantity(newCartDetail.getQuantity() + quantity);
                    this.cartDetailRepository.save(newCartDetail);
                }

            }

        }
    }

    public void handleDeleteProductFromCart(Long cartDetailId, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(cartDetailId);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();

            Cart currentCart = cartDetail.getCart();
            // delete cart-detail
            this.cartDetailRepository.deleteById(cartDetailId);

            // update cart
            if (currentCart.getSum() > 1) {
                // update current cart
                int s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                // delete cart (sum = 1)
                currentCart.setSum(0);
                this.cartRepository.save(currentCart);
                session.setAttribute("sum", 0);
            }
        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverAddress,
            String receiverPhone) {
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> lCartDetails = cart.getCartDetails();
            double totalPrice = 0;
            if (lCartDetails != null) {
                for (CartDetail x : lCartDetails) {
                    totalPrice += x.getPrice() * x.getQuantity();
                }
            }

            Order order = new Order();
            order.setUser(user);
            order.setReceiverName(receiverName);
            order.setReceiverAddress(receiverAddress);
            order.setReceiverPhone(receiverPhone);
            order.setStatus("PENDING");
            order.setTotalPrice(totalPrice);
            order = this.orderRepository.save(order);

            if (lCartDetails != null) {
                for (CartDetail x : lCartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setPrice(x.getPrice());
                    orderDetail.setProduct(x.getProduct());
                    orderDetail.setQuantity(x.getQuantity());

                    this.orderDetailRepository.save(orderDetail);
                }

                for (CartDetail x : lCartDetails) {
                    this.cartDetailRepository.deleteById(x.getId());
                }
                session.setAttribute("sum", 0);
            }

            cart.setSum(0);

        }
    }

    public Page<Order> fetchOrders(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    public List<Order> fetchOrdersByUser(User user) {
        return this.orderRepository.findByUser(user);
    }

    public Order findOrderById(Long id) {
        return this.orderRepository.findById(id).get();
    }

    public Order createOrder(Order order) {
        return this.orderRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        this.orderRepository.deleteById(id);
    }

    public void handleDeleteOrder(Long id) {
        Order order = this.orderRepository.findById(id).get();
        if (order != null) {
            List<OrderDetail> lOrderDetails = order.getOrderDetails();
            if (lOrderDetails != null) {
                for (OrderDetail orderDetail : lOrderDetails) {
                    this.orderDetailRepository.deleteById(orderDetail.getId());
                }
            }
            this.orderRepository.deleteById(id);
        }

    }
}
