import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

public class Negativo_ implements PlugInFilter {
	int janelaLarg;
	int janelaAlt;
	@Override
	public void run(ImageProcessor imagem) {
		int altura = imagem.getHeight();
		int largura = imagem.getWidth();
		
		ByteProcessor imgSaida = new ByteProcessor(largura, altura);
		
		for (int x = 0; x < largura; x++) {
			for (int y = 0; y < altura; y++) {
		        int negativo = 255 - imagem.getPixel(x, y);
		        imgSaida.putPixel(x, y, negativo);
			}
		}
		
		ImagePlus plus = new ImagePlus("Imagem após o filtro de Negativo! ", imgSaida);
		   plus.show( );
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		GenericDialog janela = new GenericDialog("Filtro Negativo:");
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
