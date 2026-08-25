package uz.imaan;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.imaan.entity.Firma;
import uz.imaan.entity.Maxsulot;
import uz.imaan.entity.UserProfile;
import uz.imaan.entity.UserSession;

import java.util.List;

public class MarketBot extends TelegramLongPollingBot {
    private final DatabaseV db = DatabaseV.getInstance();

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                handleMessage(update.getMessage());
            } else if (update.hasCallbackQuery()) {
                handleCallBack(update.getCallbackQuery());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(Message message) {
        long chatId = message.getChatId();
        String matn = message.getText();
        UserSession sessiya = db.sessiyaTop(chatId);
//
        if (matn.equals("/start")) {
            sessiya.setState(BotState.MAIN_MENU);
            yuborish(chatId, "rayxona dan salooom");
            return;
        }

        switch (sessiya.getState()) {
            case REGISTER_ISM:
                ism(chatId, matn, sessiya);
                break;
            case REGISTER_TELEFON:
                telefon(chatId, matn, sessiya);
                break;
            default:
                if (matn.equals("/dokon")) {
                    firmalarniKorish(chatId);
                } else {
                    yuborish(chatId, "notogri buyruq");
                }

        }
    }

    private void handleCallBack(CallbackQuery callback) {
        long chatId = callback.getMessage().getChatId();
        String data = callback.getData();
        System.out.println("data : " + data);
        UserSession sessiya = db.sessiyaTop(chatId);

        String[] qism = data.split(":");
        String amal = qism[0];

        switch (amal) {
            case "dokon":
                firmalarniKorish(chatId);
                break;
            case "firma":
                int firmaId = Integer.parseInt(qism[1]);
                sessiya.setTanlanganFirmaId(firmaId);
                maxsulotlarniKKorsatish(chatId, firmaId);
                break;
            case "mahsulot":
                int fId = Integer.parseInt(qism[1]);
                int maxsulotId = Integer.parseInt(qism[2]);
                Maxsulot maxsulot = db.maxsulotTop(fId, maxsulotId);
                if (maxsulot != null) {
                    db.savatgaQoshish(chatId, maxsulot, 1);
                    yuborish(chatId, maxsulot.getName() + " savatga qoshildi");
                }
                break;
            default:
                yuborish(chatId, "nomalum amal");

        }
    }

    private void ism(long chatId, String ism, UserSession sessiya) {
        try {
            if (ism.length() < 3) {
                throw new IllegalArgumentException("ism juda qisqa");
            }
            UserProfile profile = db.profilTop(chatId);
            profile.setName(ism);
            sessiya.setState(BotState.REGISTER_TELEFON);
            yuborish(chatId, "rahmat " + ism + " endi tel raqamni yuvorin");
        } catch (IllegalArgumentException e) {
            yuborish(chatId, "ism togri kirtilmagan qayta urining");
        }
    }

    private void yuborish(long chatId, String matn) {
        SendMessage msg = new SendMessage();
        msg.setChatId(String.valueOf(chatId));
        msg.setText(matn);

        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void telefon(long chatId, String telefon, UserSession session) {
        try {
            if (!telefon.matches("^\\+?\\d{9,13}$")) {
                throw new IllegalArgumentException("telefon formati notogri");
            }

            UserProfile profile = db.profilTop(chatId);
            profile.setPhoneNumber(telefon);
            session.setState(BotState.MAIN_MENU);
            yuborish(chatId, "royxatdan otdingiz");
        } catch (IllegalArgumentException e) {
            yuborish(chatId, "notogri tel raqam masalan : +998901234567");
        }
    }

    private void firmalarniKorish(long chatId) {
        List<Firma> firmalar = db.hamaFirmalar();

        if (firmalar.isEmpty()) {
            yuborish(chatId, "firma mavjud emas !");
            return;
        }

        SendMessage msg = new SendMessage();

        msg.setChatId(String.valueOf(chatId));
        msg.setText("firmalardan birini tanlang : ");
        msg.setReplyMarkup(KeyboardUtil.firmalarKeybordi(firmalar));

        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void maxsulotlarniKKorsatish(long chatId, int firmaId) {
        List<Maxsulot> maxsulotlar = db.firmaMaxsulot(firmaId);

        SendMessage msg = new SendMessage();

        msg.setChatId(String.valueOf(chatId));
        msg.setText("Maxsulotlar : ");
        msg.setReplyMarkup(KeyboardUtil.mahsulotKeyboard(maxsulotlar));

        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "hamidani_projecti_bot";
    }

    @Override
    public String getBotToken() {
        return "8960982148:AAH9dmpzdcpsLgTgPLU4GOGmK78V_pZOq9c";
    }
}
