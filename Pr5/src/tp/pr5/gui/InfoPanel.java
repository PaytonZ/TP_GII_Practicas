/**
 * 
 */
package tp.pr5.gui;

import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.InformacionObservadores;
import tp.pr5.ObservaSalidaRobot;

/**
 * This is the class of panel that is below that displays information about the robot.
 * @author David Garcia y Javier Toledano
 *
 */
@SuppressWarnings("serial")
public class InfoPanel extends JPanel implements ObservaSalidaRobot {

	private JLabel loQueDiceELRobot;
	
	/**
	 * Default builder.
	 */
	public InfoPanel()
	{
		loQueDiceELRobot=new JLabel();
		add(loQueDiceELRobot);
	}

	/**
	 * This method inserts a text panel JLabel.
	 */
	public void insertaUnTexto(String g)
	{
		loQueDiceELRobot.setText(g);
	}

	/**
	 * This method do the update and get the message for the JLabel.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if (arg1!=null)
		{
			if (arg1.getClass()==InformacionObservadores.class)
			{
				Object a = ((InformacionObservadores)arg1).buscarMiInfo("MensajeGUI");
				if (a!=null)
				{
					loQueDiceELRobot.setText(((String)a));
				}
			}
		}		
	}

	
	

	

}
