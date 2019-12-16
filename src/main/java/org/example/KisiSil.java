package org.example;

import com.vaadin.ui.*;

/**
 * KisiSil
 *
 * @author Şafak Taşkın
 * @since 1.0.0
 */
public class KisiSil extends Window{
    public KisiSil() {
        super("Kişi Sil");
        this.center();
        VerticalLayout verticalLayout = new VerticalLayout();
        this.setContent(verticalLayout);
        setModal(true);

        TextField kisiSilIdTextField = new TextField("Silinecek Kişi No");
        verticalLayout.addComponent(kisiSilIdTextField);

        Button kisiyiListedenSil = new Button("Sil");
        TextField finalKisiSil = kisiSilIdTextField;
        kisiyiListedenSil.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Kisiler kisiler = new Kisiler();
                kisiler.setId(Integer.parseInt(finalKisiSil.getValue()));

                VeritabaniIslemleri veritabaniIslemleri = new VeritabaniIslemleri();
                veritabaniIslemleri.deletePerson(kisiler);
                Notification.show("Kişi Silindi!");

                KisiListele kisiListele = new KisiListele();
                MyUI myUI = new MyUI();
                kisiListele.tableListele(myUI.table);
            }
        });
        verticalLayout.addComponents(kisiyiListedenSil);
    }

}
