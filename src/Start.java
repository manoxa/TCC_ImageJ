import java.io.IOException;

public class Start {

	public static void main(String[] args) throws IOException {

		String arquivo = "C:\\img2.jpg";
		
		int minhaImegem[][] = ImagemUtil.abrirImagemTonsDeCinza(arquivo);
		ImagemUtil.visualizarImagem(minhaImegem, "Imagem Aberta!");
		
	}
}
