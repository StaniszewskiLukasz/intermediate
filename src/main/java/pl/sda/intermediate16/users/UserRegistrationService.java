package pl.sda.intermediate16.users;

import org.apache.commons.codec.digest.DigestUtils;

public class UserRegistrationService {
    UserDAO userDAO;
    //to jest sposób na ograniczenie instancji userDAO do jednej

    public UserRegistrationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void register(UserRegistrationDTO dto) {
        if(userExists(dto, userDAO)){
           throw new UserExistsException("Uzytkownik " + dto.getEMail() + "exists") ;
        }else{
            User user = new User();
            rewriteDataToUser(dto, user);
            userDAO.saveUser(user);
        }
    }

    private void rewriteDataToUser(UserRegistrationDTO dto, User user) {
        user.setBirthdate(dto.getBirthDate().trim());
        user.setLogin(dto.getEMail().trim());
        user.setName(dto.getFirstName().trim());
        user.setPasswordHash(DigestUtils.sha512Hex(dto.getPassword().trim()));
        user.setPesel(dto.getPesel().trim());
        user.setPhone(dto.getPhone().trim());
        user.setSurname(dto.getLastName().trim());
        UserAddress userAddress = new UserAddress();
        userAddress.setCity(dto.getCity().trim());
        userAddress.setCountry(dto.getCountry().trim());
        userAddress.setStreet(dto.getStreet().trim());
        userAddress.setZipCode(dto.getZipCode().trim());
        user.setUserAddress(userAddress);
    }

    private boolean userExists(UserRegistrationDTO userRegistrationDTO, UserDAO userDAO) {
        return userDAO.getUserList().stream()
                .map(User::getLogin)
                .anyMatch(l -> l.equals(userRegistrationDTO.getEMail()));
    }
}
