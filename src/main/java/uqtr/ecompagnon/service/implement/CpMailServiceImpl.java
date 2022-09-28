package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.CpMail;
import uqtr.ecompagnon.repository.CpMailRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
@AllArgsConstructor
public class CpMailServiceImpl implements uqtr.ecompagnon.service.CpMailService {

    private final CpMailRepository cP_MailRepository;

    @Override
    public CpMail addCpMail(CpMail cpMail) {
        return cP_MailRepository.save(cpMail);
    }

    @Override
    public CpMail editCpMail(CpMail cpMail) {
        return null;
    }

    @Override
    public CpMail getCpMailByMail(String mail) {
        return cP_MailRepository.findByCpeMail(mail);
    }

    @Override
    public CpMail findByCpeCP(String cp) {
        return cP_MailRepository.findByCpeCP(cp);
    }


    @Override
    public Optional<CpMail> getCpMailById(Long id) {
        return cP_MailRepository.findById(id);
    }

    @Override
    public Boolean existsCpMailById(Long id) {
        return cP_MailRepository.existsById(id);
    }

    @Override
    public Boolean existsCpMailByMailAndCp(String MailUsers, String cp) {
        return cP_MailRepository.existsByCpeMailAndAndCpeCP(MailUsers, cp);
    }

    @Override
    public Optional<List<CpMail>> getActiveCpMail() {
        return Optional.empty();
    }

    @Override
    public Iterable<CpMail> getAllCpMail() {
        return cP_MailRepository.findAll();
    }

    @Override
    public void deleteCpMail(Long id) {
        cP_MailRepository.deleteById(id);

    }

    @Override
    public void deleteByCpeCP(String cp) {
        cP_MailRepository.deleteByCpeCP(cp);
    }

    @Override
    public <S extends CpMail> Iterable<S> saveAll(Iterable<S> iterable) {
        return cP_MailRepository.saveAll(iterable);
    }

}
