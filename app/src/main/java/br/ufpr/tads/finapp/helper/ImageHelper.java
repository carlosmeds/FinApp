package br.ufpr.tads.finapp.helper;

import br.ufpr.tads.finapp.R;

public class ImageHelper {
    private static int TYPE0 = R.drawable.money;
    private static int TYPE1 = R.drawable.transfer;
    private static int TYPE2 = R.drawable.knowledge;
    private static int TYPE3 = R.drawable.parasol;
    private static int TYPE4 = R.drawable.house;
    private static int TYPE5 = R.drawable.heartbeat;
    private static int TYPE6 = R.drawable.file;


    public ImageHelper() {
    }

    public int setImage(Long type){
        switch (type.intValue()){
            case 0:
                return TYPE0;
            case 1:
                return TYPE1;
            case 2:
                return TYPE2;
            case 3:
                return TYPE3;
            case 4:
                return TYPE4;
            case 5:
                return TYPE5;
            case 6:
                return TYPE6;

        }
        return TYPE6;
    }
}
