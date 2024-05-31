package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// bu sınıfın metodları bir işlem (transaction) içinde çalışır ve
// herhangi bir hata durumunda yapılan değişiklikler geri alınır.
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);

}

//findUserByEmail metodu, e-posta adresine göre bir kullanıcıyı bulmak için kullanılır.

//Optional<User> döndürür, bu da kullanıcı bulunamazsa null yerine boş bir değer döndürebileceği anlamına gelir.
//Bu, null kontrolü yapmayı daha güvenli hale getirir.