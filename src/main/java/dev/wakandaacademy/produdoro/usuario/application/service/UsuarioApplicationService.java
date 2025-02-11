package dev.wakandaacademy.produdoro.usuario.application.service;

import javax.validation.Valid;

import dev.wakandaacademy.produdoro.usuario.application.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.produdoro.credencial.application.service.CredencialService;
import dev.wakandaacademy.produdoro.pomodoro.application.service.PomodoroService;
import dev.wakandaacademy.produdoro.usuario.application.api.UsuarioCriadoResponse;
import dev.wakandaacademy.produdoro.usuario.application.api.UsuarioNovoRequest;
import dev.wakandaacademy.produdoro.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class UsuarioApplicationService implements UsuarioService {
	private final PomodoroService pomodoroService;
	private final CredencialService credencialService;
	private final UsuarioRepository usuarioRepository;

	@Override
	public UsuarioCriadoResponse criaNovoUsuario(@Valid UsuarioNovoRequest usuarioNovo) {
		log.info("[inicia] UsuarioApplicationService - criaNovoUsuario");
		var configuracaoPadrao = pomodoroService.getConfiguracaoPadrao();
		credencialService.criaNovaCredencial(usuarioNovo);
		var usuario = new Usuario(usuarioNovo,configuracaoPadrao);
		usuarioRepository.salva(usuario);
		log.info("[finaliza] UsuarioApplicationService - criaNovoUsuario");
		return new UsuarioCriadoResponse(usuario);
	}


	@Override
	public UsuarioCriadoResponse buscaUsuarioPorId(UUID idUsuario) {
		log.info("[inicia] UsuarioApplicationService - buscaUsuarioPorId");
		Usuario usuario = usuarioRepository.buscaUsuarioPorId(idUsuario);
		log.info("[finaliza] UsuarioApplicationService - buscaUsuarioPorId");
		return new UsuarioCriadoResponse(usuario);
	}

	@Override
	public void mudarStatusParaPausaLonga(String email, UUID idUsuario) {
		log.info("[inicia] UsuarioApplicationService - mudarStatusParaPausaLonga");
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(email);
		usuarioRepository.buscaUsuarioPorId(idUsuario);
		usuario.mudarStatusPausaLonga(idUsuario);
		usuarioRepository.salva(usuario);
		log.info("[finaliza] UsuarioApplicationService - mudarStatusParaPausaLonga");
	}

	@Override
	public void mudarStatusParaFoco(String email, UUID idUsuario) {
		log.info("[inicia] UsuarioApplicationService - mudarStatusParaFoco");
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(email);
		usuarioRepository.buscaUsuarioPorId(idUsuario);
		usuario.mudarStatusParaFoco(idUsuario);
		usuarioRepository.salva(usuario);
		log.info("[finaliza] UsuarioApplicationService - mudarStatusParaFoco");
	}

	@Override
	public void mudarStatusParaPausaCurta(String email, UUID idUsuario) {
		log.info("[inicia] UsuarioApplicationService - mudarStatusParaPausaCurta");
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(email);
		usuarioRepository.buscaUsuarioPorId(idUsuario);
		usuario.mudarStatusPausaCurta(idUsuario);
		usuarioRepository.salva(usuario);
		log.info("[finaliza] UsuarioApplicationService - mudarStatusParaPausaCurta");
	}


}
