package br.feevale.view;

import br.feevale.model.ItemAtendimento;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class ItemCell extends ListCell<ItemAtendimento> {

    @Override
    protected void updateItem(ItemAtendimento item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
            setText(null);
            return;
        }

        ImageView imageView = new ImageView();
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        try {
            String imagePath = item.getImagePath();
            if (imagePath != null && !imagePath.isEmpty()) {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                imageView.setImage(image);
            }
        } catch (Exception e) {
            System.out.println("Image not found: " + item.getImagePath());
        }

        Text name = new Text(item.getNome());
        Text price = new Text("R$ " + String.format("%.2f", item.getValor()));

        VBox box = new VBox(imageView, name, price);
        box.setSpacing(5);

        setGraphic(box);
    }
}
