package school.work.skillswap_b.service.interfaces;

import school.work.skillswap_b.dto.CreateSkillOfferRequest;
import school.work.skillswap_b.dto.SkillOfferResponse;

import java.util.List;

public interface SkillOfferService {

    List<SkillOfferResponse> getAll();

    SkillOfferResponse getById(long id);

    SkillOfferResponse create(CreateSkillOfferRequest request);

    SkillOfferResponse update(long id, CreateSkillOfferRequest request);

    void delete(long id);
}