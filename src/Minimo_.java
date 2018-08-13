import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

public class Minimo_ implements PlugInFilter {

	int janelaLarg;
	int janelaAlt;

	public void run(ImageProcessor imagem) {

		int altura = imagem.getHeight();
		int largura = imagem.getWidth();

		int n = 3;
		int m = 3;
		int min;

		ByteProcessor imgSaida = new ByteProcessor(largura, altura);

		for (int x = 0; x < largura; x++) {
			for (int y = 0; y < altura; y++) {
				min = 100000000;
				for (int i = -n / 2; i <= n / 2; i++) {
					for (int j = -m / 2; j <= m / 2; j++) {
						if (x + i >= 0 && x + i < largura && y + j >= 0
								&& y + j < altura)
							if (imagem.getPixel(x + i, y + j) < min)
								min = imagem.getPixel(x + i, y + j);
					}
				}
				imgSaida.putPixel(x, y, min);
			}
		}

		ImagePlus plus = new ImagePlus("Imagem após o filtro de Minimo! ",
				imgSaida);
		plus.show();
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		GenericDialog janela = new GenericDialog("Filtro Minimo:");
		janela.addMessage("Entre com os dados do Filtro.");
		janela.addNumericField("Largura: ", 3, 0);
		janela.addNumericField("Altura: ", 3, 0);

		janela.showDialog();
		if (janela.wasCanceled()) {
			return PlugInFilter.DONE;
		}

		janelaAlt = (int) janela.getNextNumber();
		janelaLarg = (int) janela.getNextNumber();

		return PlugInFilter.DOES_8G;
	}

}
