package org.example;

import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

/**
 * YeniPopupWindow
 *
 * @author Şafak Taşkın
 * @since 1.0.0
 */
public class YeniKayitEkle extends Window {

    public YeniKayitEkle() {
        super("Yeni Kayıt");
        this.center();
        VerticalLayout layoutWindow = new VerticalLayout();
        this.setContent(layoutWindow);
        setModal(true);

        TextField tf1 = new TextField("Adı");
        tf1.setIcon(FontAwesome.USER);
        tf1.setRequired(true);
        tf1.addValidator(new NullValidator("Zorunludur!", false));

        TextField tf2 = new TextField("Soyadı");
        tf2.setIcon(FontAwesome.USER);
        tf2.setRequired(true);
        tf2.addValidator(new NullValidator("Zorunludur!", false));

        TextField tf3 = new TextField("Şehir");
        tf3.setIcon(FontAwesome.ROAD);

        TextField tf4 = new TextField("Telefon");
        tf4.setIcon(FontAwesome.PHONE);
        tf4.setRequired(true);
        tf4.addValidator(new NullValidator("Zorunludur!", false));

        layoutWindow.addComponents(tf1, tf2, tf3, tf4);

        Button tamam = new Button("Kaydet");
        layoutWindow.addComponent(tamam);
        layoutWindow.setComponentAlignment(tamam, Alignment.BOTTOM_RIGHT);
        tamam.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                Kisiler kisiler = new Kisiler();
                kisiler.setName(tf1.getValue());
                kisiler.setSurname(tf2.getValue());
                kisiler.setCity(tf3.getValue());
                kisiler.setPhone(tf4.getValue());

                VeritabaniIslemleri veritabaniIslemleri = new VeritabaniIslemleri();
                veritabaniIslemleri.addPerson(kisiler);
                Notification.show("Kişi Eklendi");

            }
        });
    }

}



