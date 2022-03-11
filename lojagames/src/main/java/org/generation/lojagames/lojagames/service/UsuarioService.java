package org.generation.lojagames.lojagames.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.lojagames.lojagames.model.Usuario;
import org.generation.lojagames.lojagames.model.UsuarioLogin;
import org.generation.lojagames.lojagames.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario){
	if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
		return Optional.empty();
	usuario.setSenha(criptografarSenha(usuario.getSenha()));
	return Optional.of(usuarioRepository.save(usuario));	
	}
	
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin){
	Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		
		if(usuario.isPresent()) {
			
			usuarioLogin.get().setId(usuario.get().getId());
			usuarioLogin.get().setNome(usuario.get().getNome());
			usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
			usuarioLogin.get().setSenha(usuario.get().getSenha());
			
			return usuarioLogin;
		}
	return Optional.empty();
	}
	private String criptografarSenha(String senha) {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	return encoder.encode(senha);
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	return encoder.matches(senhaDigitada, senhaBanco);
	}

	private String gerarBasicToken(String usuario, String senha) {
	String token = usuario + ":" + senha;
	byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
	return "Basic " + new String(tokenBase64);
	}

	
}
