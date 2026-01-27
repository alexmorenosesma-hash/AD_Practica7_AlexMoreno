package es.alexmoreno.security;


import es.alexmoreno.domain.Usuario;
import es.alexmoreno.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Simple helper to get the current logged-in user.
 * Uses Spring Security to find who is authenticated.
 */
@Component
public class UserContext {

    @Autowired
    private UsuarioRepository userRepository;

    /**
     * Gets the ID of the currently logged-in user.
     * Throws exception if no user is logged in.
     */
    public Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("No user is logged in");
        }

        Object principal = auth.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new IllegalStateException("User is not authenticated");
        }
        
        String username = ((UserDetails) principal).getUsername();
        
        // Step 3: Find the user in database and return their ID
        Usuario user = userRepository.findByUsuarioNombre(username)
                .orElseThrow(() -> new IllegalStateException("User not found: " + username));
        
        return user.getIdUsuario();
    }

    /**
     * Gets the full User object of the currently logged-in user.
     * Throws exception if no user is logged in.
     */
    public Usuario getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("No user is logged in");
        }

        Object principal = auth.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new IllegalStateException("User is not authenticated");
        }
        
        String username = ((UserDetails) principal).getUsername();
        
        return userRepository.findByUsuarioNombre(username)
                .orElseThrow(() -> new IllegalStateException("User not found: " + username));
    }
}
