package com.Restaurant.management.system.services.customer;

import com.Restaurant.management.system.dtos.CategoryDto;
import com.Restaurant.management.system.dtos.ProductDto;
import com.Restaurant.management.system.dtos.ReservationDto;
import com.Restaurant.management.system.entities.Category;
import com.Restaurant.management.system.entities.Product;
import com.Restaurant.management.system.entities.Reservation;
import com.Restaurant.management.system.entities.User;
import com.Restaurant.management.system.enums.ReservationStatus;
import com.Restaurant.management.system.repositories.CategoryRepository;
import com.Restaurant.management.system.repositories.ProductRepository;
import com.Restaurant.management.system.repositories.ReservationRepository;
import com.Restaurant.management.system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoriesByName(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title) {
        return productRepository.findAllByCategoryIdAndNameContaining(categoryId, title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDto postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getCustomerId());
        if(optionalUser.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setTableType(reservationDto.getTableType());
            reservation.setDateTime(reservationDto.getDateTime());
            reservation.setDescription(reservationDto.getDescription());
            reservation.setUser(optionalUser.get());
            reservation.setReservationStatus(ReservationStatus.PENDING);

             Reservation postedReservation = reservationRepository.save(reservation);
             ReservationDto postedReservationDto = new ReservationDto();
             postedReservationDto.setId(postedReservation.getId());

             return postedReservationDto;
        }
        return null;
    }

    @Override
    public List<ReservationDto> getReservationsByUser(Long customerId) {
        return reservationRepository.findAllByUserId(customerId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }
}
