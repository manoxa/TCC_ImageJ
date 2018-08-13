import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

import java.awt.Rectangle;

public class Media_ implements PlugInFilter {

	int janelaLarg;
	int janelaAlt; 
	
	public void run(ImageProcessor img) {
		Rectangle ret = img.getRoi();
		int largura = img.getWidth();
		int altura = img.getHeight();
		int quantidade = janelaAlt * janelaLarg;
		
		ByteProcessor imgSaida = new ByteProcessor(largura, altura);
		
		for(int y=ret.y; y < ret.y+ret.height; y++){
			for(int x=ret.x; x < ret.x + ret.width; x++){
				int soma = 0;
				for(int j= -(janelaAlt/2); j<= (janelaAlt/2); j++){
					for(int i= -(janelaLarg/2); i<= (janelaLarg/2); i++){
						soma += img.getPixel(x+ i , y + j);
					}
				}
				imgSaida.putPixel(x, y, soma / quantidade);
			}
		}
		
		ImagePlus plus= new ImagePlus("filtro da média", imgSaida);
		plus.show();
		
	}

	public int setup(String arg0, ImagePlus arg1) {
		GenericDialog tela = new GenericDialog("Filtro da media");
		tela.addMessage("Entre com os dados do filtro");
		tela.addNumericField("Largura", 3, 0);
		tela.addNumericField("Altura", 3, 0);
		
		tela.showDialog();
		if(tela.wasCanceled()){
			return PlugInFilter.DONE;
		}
		
		janelaLarg = (int) tela.getNextNumber();
		janelaAlt = (int) tela.getNextNumber();
		
		return PlugInFilter.DOES_8G;
	}


}
