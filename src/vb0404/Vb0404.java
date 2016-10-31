
package vb0404;

// Voorbeeld 0404  Kassa
// GridLayout
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;  
   
public class Vb0404 extends JFrame {
  public static void main( String args[] ) {
    JFrame frame = new Vb0404();
    frame.setSize( 300, 100 );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setTitle( "Voorbeeld 0404: Kassa" );
    frame.setContentPane( new KassaPaneel() );
    frame.setVisible( true );
  }
}

// Het paneel voor de kassa
class KassaPaneel extends JPanel {
  private JLabel bedragLabel, subtotaalLabel;
  private JTextField bedragVak, subtotaalVak;
  private Kassa kassa;
  
  public KassaPaneel() {
    // Kies GridLayout
    setLayout( new GridLayout( 2, 2, 10, 10 ) );
    Border border = BorderFactory.createEmptyBorder( 5, 5, 5, 5 );
    setBorder( border );

    // Maak een kassa:
    kassa = new Kassa();
    
    // Maak de tekstvakken
    bedragVak = new JTextField( 10 );
    bedragVak.addActionListener( new BedragVakHandler() );
    bedragVak.setHorizontalAlignment( JTextField.RIGHT );

    subtotaalVak = new JTextField( 10 );
    subtotaalVak.setBackground( Color.YELLOW );
    
    // Zorg dat gebruiker niet kan wijzigen en lijn rechts uit
    subtotaalVak.setEditable( false );
    subtotaalVak.setHorizontalAlignment( JTextField.RIGHT );    

    
    // Maak de labels
    bedragLabel = new JLabel( "Voer bedrag in" );
    subtotaalLabel = new JLabel( "Subtotaal" );

    // Voeg de componenten toe aan het paneel
    add( bedragLabel);
    add( bedragVak );
    add( subtotaalLabel );
    add( subtotaalVak );
  }

  class BedragVakHandler implements ActionListener {
    public void actionPerformed( ActionEvent e ) {
      String invoer = bedragVak.getText();
      double invoerBedrag = Double.parseDouble( invoer );

      // Geef het bedrag door aan de kassa
      kassa.telOp( invoerBedrag );
      
      // Vraag om het subtotaal en formatteer dit
      double st = kassa.getSubtotaal();
      subtotaalVak.setText( String.format( "%.2f", st ) );
      
      // Maak invoervak leeg
      bedragVak.setText( "" );
    }
  }
}