package cf.bankservice.mappers;

import cf.bankservice.dto.RequestCompteDto;
import cf.bankservice.dto.ResponseCompteDto;
import cf.bankservice.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    public Compte DTO_to_Entity(RequestCompteDto requestCompteDto) {
        Compte compte = new Compte();
        BeanUtils.copyProperties(requestCompteDto, compte);
        return compte;
    }

    public ResponseCompteDto Entity_to_DTO(Compte compte) {
        ResponseCompteDto responseCompteDto = new ResponseCompteDto();
        BeanUtils.copyProperties(compte, responseCompteDto);
        return responseCompteDto;
    }
}
