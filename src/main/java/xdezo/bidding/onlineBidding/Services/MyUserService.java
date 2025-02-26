package xdezo.bidding.onlineBidding.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xdezo.bidding.onlineBidding.Model.User;
import xdezo.bidding.onlineBidding.Principal.UserPrincipal;
import xdezo.bidding.onlineBidding.Repo.UserRepo;

@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);

        if(user==null){
            System.out.println("No username Found");
            throw new UsernameNotFoundException("No username Found");

        }
        else{
            return new UserPrincipal(user);
        }
    }
}
