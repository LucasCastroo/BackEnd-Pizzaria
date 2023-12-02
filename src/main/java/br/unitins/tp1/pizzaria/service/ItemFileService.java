package br.unitins.tp1.pizzaria.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ItemFileService implements FileService{

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;

    private static final List<String> SUPPORTED_MIME_TYPES =
            Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif");
    private final String PATH = "." + File.separator + "img" + File.separator + "item" + File.separator;

    public String gerarNomeArquivo(String extensao) {
        String nome = UUID.randomUUID() + "." + extensao;
        if (Paths.get(PATH).resolve(nome).toFile().exists()) {
            // praticamente impossivel isso acontecer antes da morte térmica do universo, mas vai que né
            nome = gerarNomeArquivo(extensao);
        }
        return nome;
    }

    @Override
    public String salvar(String nomeArquivo, byte[] arquivo) throws IOException {
        verificarTamanhoImagem(arquivo);

        verificarTipoImagem(nomeArquivo);
        Path diretorio = Paths.get(PATH);
        Files.createDirectories(diretorio);

        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        String extensao = mimeType.substring(mimeType.lastIndexOf('/') + 1);
        String novoNomeArquivo = gerarNomeArquivo(extensao);
        Path filePath = diretorio.resolve(novoNomeArquivo);

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(arquivo);
        }

        return filePath.toFile().getName();
    }

    @Override
    public File obter(String nomeArquivo) {
        File file = new File(PATH + nomeArquivo);
        return file;
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
