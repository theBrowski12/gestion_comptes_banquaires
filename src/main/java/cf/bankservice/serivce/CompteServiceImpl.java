package cf.bankservice.serivce;

import cf.bankservice.dto.RequestCompteDto;
import cf.bankservice.dto.ResponseCompteDto;
import cf.bankservice.entities.Compte;
import cf.bankservice.mappers.CompteMapper;
import cf.bankservice.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompteServiceImpl implements CompteService {
    private CompteRepository compteRepository;
    private CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public ResponseCompteDto Add_compte(RequestCompteDto requestCompteDto) {
        Compte compte= compteMapper.DTO_to_Entity(requestCompteDto);
        Compte SavedCompte = compteRepository.save(compte);
        return compteMapper.Entity_to_DTO(SavedCompte);
    }

    @Override
    public List<ResponseCompteDto> GetAllCompte() {
        List<Compte> comptes= compteRepository.findAll();
        List<ResponseCompteDto> CompteDtos= new ArrayList<>();
        for(Compte c: comptes){
            CompteDtos.add(compteMapper.Entity_to_DTO(c));
        }
        return CompteDtos;
    }

    @Override
    public ResponseCompteDto GetCompteById(Integer id) {
        Compte compte = compteRepository.findById(id).orElse(null);
        return compteMapper.Entity_to_DTO(compte);
    }

    @Override
    public ResponseCompteDto UpdateCompte(Integer id, RequestCompteDto requestCompteDto) {
        Compte nv_compte= compteMapper.DTO_to_Entity(requestCompteDto);

        Compte compte = compteRepository.findById(id).orElse(null);

        if(nv_compte.getNom()!= null) compte.setNom(nv_compte.getNom());
        if(nv_compte.getTel()!= null) compte.setTel(nv_compte.getTel());
        if(nv_compte.getSolde()!= null) compte.setSolde(nv_compte.getSolde());

        Compte savedCompte = compteRepository.save(compte);

        return compteMapper.Entity_to_DTO(savedCompte);
    }

    @Override
    public void DeleteCompteByID(Integer id) {
        compteRepository.deleteById(id);
    }

    @Override
    public ResponseCompteDto crediter(Integer id, Double montant) {
        Compte compte = compteRepository.findById(id).orElseThrow();
        compte.setSolde(compte.getSolde()+montant);
        Compte saved = compteRepository.save(compte);
        return compteMapper.Entity_to_DTO(saved);
    }


    @Override
    public ResponseCompteDto Debiter(Integer id, Double montant) {
        Compte compte = compteRepository.findById(id).orElseThrow();
        compte.setSolde(compte.getSolde()-montant);
        Compte saved = compteRepository.save(compte);
        return compteMapper.Entity_to_DTO(saved);
    }
}
