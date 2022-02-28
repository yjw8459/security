package yjw.test.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yjw.test.security.vo.User;

//CRUD 함수를 JpaRepository가 들고 있음
//@Repository가 없어도 IoC가 된다. JpaRepository가 상속이기 때문에
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
}
