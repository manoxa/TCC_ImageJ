import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

public class Maximo_ implements PlugInFilter {
	
	int janelaAlt;
	int janelaLag;

	public void run(ImageProcessor imagem) {

		int altura = imagem.getHeight();
		int largura = imagem.getWidth();

		int n = 3;
		int m = 3;
		int max = 0;

		ByteProcessor imgSaida = new ByteProcessor(largura, altura);

		for (int x = 0; x < largura; x++) {
			for (int y = 0; y < altura; y++) {
				max=0;
				for (int i = -n / 2; i <= n / 2; i++) {
					for (int j = -m / 2; j <= m / 2; j++) {
						if (x + i >= 0 && x + i < largura && y + j >= 0
								&& y + j < altura)
							if (imagem.getPixel(x + i, y + j) > max)
						max = imagem.getPixel(x + i, y + j);
					}
				}
				imgSaida.putPixel(x,y, max);
			}
		}
		
		ImagePlus plus = new ImagePlus("Imagem após o filtro de Maximo! ", imgSaida);
		   plus.show( );
	}

	public int setup(String arg0, ImagePlus arg1) {
		GenericDialog janela = new GenericDialog("Filtro Maximo:");
		janela.addMessage("Entre com os dados do Filtro.");
		janela.addNumericField("Largura: ", 3, 0);
		janela.addNumericField("Altura: ", 3, 0);
		
		janela.showDialog();
		if(janela.wasCanceled()){
			return PlugInFilter.DONE;
		}
	
		janelaAlt = (int)janela.getNextNumber();
		janelaLag = (int)janela.getNextNumber();
		
		return PlugInFilter.DOES_8G;
	}

}
