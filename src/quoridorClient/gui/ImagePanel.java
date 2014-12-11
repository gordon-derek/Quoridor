package quoridorClient.gui;

import java.awt.*;
import javax.swing.JComponent;

class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
/*
// elsewhere
BufferedImage myImage = ImageIO.load(...);
JFrame myJFrame = new JFrame("Image pane");
myJFrame.setContentPane(new ImagePanel(myImage));
*/