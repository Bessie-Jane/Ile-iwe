package com.ilewe.ileiwe.data.security.user;

import com.ilewe.ileiwe.data.repsitory.LearningPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LearningPartyUserServiceImpl implements UserDetailsService {

    @Autowired
    private LearningPartyRepository learningPartyRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {





        return null;
    }
}
