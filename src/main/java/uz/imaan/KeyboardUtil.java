package uz.imaan;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.imaan.entity.Firma;
import uz.imaan.entity.Maxsulot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class KeyboardUtil {
    public static InlineKeyboardMarkup firmalarKeybordi(List<Firma> firmalar){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (Firma firma : firmalar){
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(firma.getName());

            btn.setCallbackData("firma:" + firma.getId());

            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(btn);
            rows.add(row);
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(rows);
        return markup;
    }

    public static InlineKeyboardMarkup mahsulotKeyboard(List<Maxsulot> maxsulotlar){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (Maxsulot maxsulot : maxsulotlar){
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(maxsulot.getName() + " - " + maxsulot.getPrice() + " so'm");
            btn.setCallbackData("mahsulot:" + maxsulot.getFirmaId() + " : " + maxsulot.getId());

            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(btn);
            rows.add(row);
        }
        InlineKeyboardButton orqaga = new InlineKeyboardButton();
        orqaga.setText("- orqaga");
        orqaga.setCallbackData("dokon");
        rows.add(Collections.singletonList(orqaga));

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(rows);
        return markup;
    }



}
