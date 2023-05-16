package com.krillinator.springSecurityLektion.rats;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krillinator.springSecurityLektion.user.UserModel;

@Service
public class RatService {
    
    private final RatRepository ratRepository;
    
    public RatService(RatRepository ratRepository) {
        this.ratRepository = ratRepository;
    }
    
    public Rat getRatById(Long id) {
        return ratRepository.findById(id).orElse(null);
    }
    
    public Rat saveRat(Rat rat) {
        return ratRepository.save(rat);
    }
    
    public List<Rat> getRatsForOwner(UserModel owner) {
        return ratRepository.findByOwner(owner);
    }
    
    public void deleteRat(Rat rat) {
        ratRepository.delete(rat);
    }
    
}
