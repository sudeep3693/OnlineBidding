package xdezo.bidding.onlineBidding.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xdezo.bidding.onlineBidding.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findByEmail(String email);


}
