package co.edu.icesi.tallerjpa.runableartefact.service;

import co.edu.icesi.tallerjpa.runableartefact.dto.IcesiUserDTO;
import co.edu.icesi.tallerjpa.runableartefact.exception.implementation.DataAlreadyExist;
import co.edu.icesi.tallerjpa.runableartefact.exception.implementation.ParameterRequired;
import co.edu.icesi.tallerjpa.runableartefact.mapper.IcesiUserMapper;
import co.edu.icesi.tallerjpa.runableartefact.model.IcesiUser;
import co.edu.icesi.tallerjpa.runableartefact.repository.IcesiRoleRepository;
import co.edu.icesi.tallerjpa.runableartefact.repository.IcesiUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class IcesiUserService {

    private final IcesiUserRepository icesiUserRepository;
    private final IcesiRoleRepository icesiRoleRepository;

    private final IcesiUserMapper icesiUserMapper;

    public String saveNewUser(IcesiUserDTO icesiUserDTO) throws DataAlreadyExist, ParameterRequired {
        IcesiUser icesiUser = icesiUserMapper.toIcesiUser(icesiUserDTO);
        icesiUser.setRole(icesiRoleRepository.findByName(icesiUserDTO.getRoleName()).get());

        validateUserEmail(icesiUser);
        validatePhoneNumber(icesiUser);
        validateEmailAndPhoneNumber(icesiUser);
        validateRole(icesiUser);

        icesiUser.setUserId(UUID.randomUUID());
        icesiUserRepository.save(icesiUser);
        return "User saved";
    }

    private void validateUserEmail(IcesiUser icesiUser) throws DataAlreadyExist {
        boolean emailAlreadyExist = icesiUserRepository.existsByEmail(icesiUser.getEmail());
        if(emailAlreadyExist) {throw new DataAlreadyExist("Email already exists");}
    }
    private void validatePhoneNumber(IcesiUser icesiUser) throws DataAlreadyExist{
        boolean phoneAlreadyExist = icesiUserRepository.existsByPhoneNumber(icesiUser.getPhoneNumber());
        if(phoneAlreadyExist) {throw new DataAlreadyExist("Phone number already exists");}
    }
    private void validateEmailAndPhoneNumber(IcesiUser icesiUser) throws DataAlreadyExist{
        boolean emailAlreadyExist = icesiUserRepository.existsByEmail(icesiUser.getEmail());
        boolean phoneAlreadyExist = icesiUserRepository.existsByPhoneNumber(icesiUser.getPhoneNumber());
        if(emailAlreadyExist && phoneAlreadyExist) {throw new DataAlreadyExist("Email and phone number already exists");}
    }
    private void validateRole(IcesiUser icesiUser) throws ParameterRequired{
        boolean roleAlreadyExist = icesiUser.getRole() == null;
        if(roleAlreadyExist) {throw new ParameterRequired("Role is required");}
    }


}
