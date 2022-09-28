package uqtr.ecompagnon.service;

import uqtr.ecompagnon.model.CpMail;

import java.util.List;
import java.util.Optional;

public interface CpMailService {


    CpMail addCpMail(CpMail cpMail);
    CpMail editCpMail(CpMail cpMail);
    CpMail getCpMailByMail(String mail);
    Optional<CpMail> getCpMailById(Long id);
    CpMail findByCpeCP(String cp);
    Boolean existsCpMailById(Long id);
    Boolean existsCpMailByMailAndCp(String MailUsers, String cp);
    Optional<List<CpMail>> getActiveCpMail();
    Iterable<CpMail> getAllCpMail();
    void deleteCpMail(Long id);
    void deleteByCpeCP(String cp);
    <S extends CpMail> Iterable<S> saveAll(Iterable<S> iterable);


}
