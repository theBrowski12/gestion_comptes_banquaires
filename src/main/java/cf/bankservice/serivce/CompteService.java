package cf.bankservice.serivce;

import cf.bankservice.dto.RequestCompteDto;
import cf.bankservice.dto.ResponseCompteDto;

import java.util.List;

public interface CompteService {
    public ResponseCompteDto Add_compte(RequestCompteDto requestCompteDto);
    public List<ResponseCompteDto> GetAllCompte();
    public ResponseCompteDto GetCompteById(Integer id);
    public ResponseCompteDto UpdateCompte(Integer id, RequestCompteDto requestCompteDto);
    public void DeleteCompteByID(Integer id);
    public ResponseCompteDto crediter(Integer id, Double montant);
    public ResponseCompteDto Debiter(Integer id, Double montant);
}
