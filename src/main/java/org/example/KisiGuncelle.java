package org.example;

import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button;

import com.vaadin.ui.*;

/**
 * KisiGuncelle
 *
 * @author Şafak Taşkın
 * @since 1.0.0
 */
public class KisiGuncelle extends Window {

    public  KisiGuncelle() {
        super("Kişi Güncelle");
        this.center();
        VerticalLayout verticalLayout = new VerticalLayout();
        this.setContent(verticalLayout);
        setModal(true);

        TextField idTextField = new TextField("Güncellenecek No:");
        TextField yeniAdTextField = new TextField("Yeni Ad:");
        TextField yeniSoyadTextField = new TextField("Yeni Soyad");
        TextField yeniSehirTextField = new TextField("Yeni Şehir");
        TextField yeniTelefonTextField = new TextField("Yeni Telefon");

        verticalLayout.addComponents(idTextField, yeniAdTextField, yeniSoyadTextField,yeniSehirTextField,yeniTelefonTextField);

        Button guncelle = new Button("Güncelle");        
        guncelle.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Kisiler kisiler = new Kisiler();

                kisiler.setId(Integer.parseInt(idTextField.getValue()));
                kisiler.setName(yeniAdTextField.getValue());
                kisiler.setPhone(yeniTelefonTextField.getValue());
                kisiler.setCity(yeniSehirTextField.getValue());
                kisiler.setSurname(yeniSoyadTextField.getValue());

                VeritabaniIslemleri veritabaniIslemleri = new VeritabaniIslemleri();
                veritabaniIslemleri.updatePerson(kisiler);
                Notification.show("Kişi Güncellendi!");

                KisiListele kisiListele = new KisiListele();
                MyUI myUI = new MyUI();
                kisiListele.tableListele(myUI.table);
            }
        });
        verticalLayout.addComponents(guncelle);
    }

}
