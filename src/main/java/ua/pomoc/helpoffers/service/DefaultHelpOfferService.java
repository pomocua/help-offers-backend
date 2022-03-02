package ua.pomoc.helpoffers.service;

import org.springframework.stereotype.Service;
import ua.pomoc.helpoffers.domain.HelpOffer;
import ua.pomoc.helpoffers.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultHelpOfferService {

    public List<HelpOffer> byPage(List<HelpOffer> helpOffers, int perPage, int page) {

        if (perPage * page > helpOffers.size()) {
            throw new BusinessException("No such amount of offers, filtered offers size: '" + helpOffers.size() + "', but total amount of pages: '" + perPage * page + "'");
        }

        List<HelpOffer> onePage = new ArrayList<>();
        List<List<HelpOffer>> allPages = new ArrayList<>();
        int pageCounter = 1;
        for (HelpOffer helpOffer : helpOffers) {
            onePage.add(helpOffer);
            if (pageCounter == perPage) {
                pageCounter = 0;
                allPages.add(onePage);
                onePage = new ArrayList<>();
            }
            pageCounter++;
        }
        if (allPages.size() < page) {
            throw new BusinessException("Page parameter: '" + page + "' exceeds total amount of pages: '" + allPages.size() + "'");
        }

        return allPages.get(page);
    }
}
