package org.example;

import com.vaadin.data.Item;
import com.vaadin.ui.Table;

import java.util.List;

/**
 * KisiListele
 *
 * @author Şafak Taşkın
 * @since 1.0.0
 */
public class KisiListele {
    VeritabaniIslemleri veritabaniIslemleri = new VeritabaniIslemleri();

    public void tableListele(Table table) {
        table.removeAllItems();
        List<Kisiler> personList = veritabaniIslemleri.findAllPerson();

        for (Kisiler kisiler : personList) {

            table.addContainerProperty("ID", Integer.class, null);
            table.addContainerProperty("AD", String.class, null);
            table.addContainerProperty("SOYAD", String.class, null);
            table.addContainerProperty("ŞEHİR", String.class, null);
            table.addContainerProperty("TELEFON", String.class, null);

            Object newItemId = table.addItem();
            Item row1 = table.getItem(newItemId);
            row1.getItemProperty("ID").setValue(kisiler.getId());
            row1.getItemProperty("AD").setValue(kisiler.getName());
            row1.getItemProperty("SOYAD").setValue(kisiler.getSurname());
            row1.getItemProperty("ŞEHİR").setValue(kisiler.getCity());
            row1.getItemProperty("TELEFON").setValue(kisiler.getPhone());

            table.setPageLength(table.size());
        }
    }

}
