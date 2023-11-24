package br.com.unitins.a1.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ItemFileService implements FileService{

    private final String PATH_USER = System.getProperty("user.home") +
            File.separator + "quarkus" +
            File.separator + "images" +
            File.separator + "item" +  File.separator;

    private static final List<String> SUPPORTED_MIME_TYPES =
            Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif");

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10mb

    @Override
    public String salvar(String nomeArquivo, byte[] arquivo) throws IOException {
        return null;
    }

    @Override
    public File obter(String nomeArquivo) {
        return null;
    }

    private void verificarTamanhoImagem(byte[] arquivo) throws IOException {
        if (arquivo.length > MAX_FILE_SIZE)
            throw new IOException("Arquivo maior que 10mb.");
    }

    private void verificarTipoImagem(String nomeArquivo) throws IOException {
        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        if (!SUPPORTED_MIME_TYPES.contains(mimeType))
            throw new IOException("Tipo de imagem não suportada.");

    }
}
