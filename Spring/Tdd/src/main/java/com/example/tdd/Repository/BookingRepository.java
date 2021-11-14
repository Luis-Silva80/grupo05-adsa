package com.example.tdd.Repository;

import com.example.tdd.Model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingModel,String> {
    Optional<BookingModel> findByReserveName(String name);

}
