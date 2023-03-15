package co.edu.icesi.tallerjpa.runableartefact.unit.service.matcher;

import co.edu.icesi.tallerjpa.runableartefact.model.IcesiRole;
import lombok.AllArgsConstructor;
import org.mockito.ArgumentMatcher;

@AllArgsConstructor
public class IcesiRoleMatcher implements ArgumentMatcher<IcesiRole> {

    IcesiRole icesiRoleLeft;

    @Override
    public boolean matches(IcesiRole icesiRoleRight) {
        return icesiRoleRight.getRoleId() != null &&
                icesiRoleRight.getName().equals(icesiRoleLeft.getName()) &&
                icesiRoleRight.getDescription().equals(icesiRoleLeft.getDescription());
    }
}
