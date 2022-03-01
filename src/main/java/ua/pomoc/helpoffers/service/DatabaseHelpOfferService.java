package ua.pomoc.helpoffers.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.pomoc.helpoffers.domain.HelpOffer;
import ua.pomoc.helpoffers.exception.BusinessException;
import ua.pomoc.helpoffers.repository.HelpOfferRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DatabaseHelpOfferService implements DatabaseService<HelpOffer, Long> {
    
    private HelpOfferRepository helpOfferRepository;

    public DatabaseHelpOfferService(HelpOfferRepository helpOfferRepository) {
        this.helpOfferRepository = helpOfferRepository;
    }

    @Override
    public List<HelpOffer> findAll() {
        List<HelpOffer> helpOffers = new ArrayList<>();
        for (HelpOffer helpOffer : helpOfferRepository.findAll()) {
            helpOffers.add(helpOffer);
        }
        return helpOffers;
    }

    @Override
    public HelpOffer findById(Long key) {
        return helpOfferRepository.findById(key).orElseGet(() -> {
            log.warn("HelpOffer not found by id " + key);
            throw new BusinessException("HelpOffer not found by id: " + key);
        });
    }

    @Override
    public HelpOffer save(HelpOffer type)  {
        return helpOfferRepository.save(type);
    }

    @Override
    public void delete(HelpOffer type) {
        helpOfferRepository.delete(type);
    }
}
