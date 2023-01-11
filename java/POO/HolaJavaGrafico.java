import java.awt.*;
public class HolaJavaGrafico{
	Frame f;
	public HolaJavaGrafico () {
		f = new Frame ("Hola Java Grafico");
		f.setSize(600, 800);
		f.setVisible(true);
	}
	public static void main(String [] args) {
		new HolaJavaGrafico ();
	}
}