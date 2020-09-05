package gui.sorters;
import model.Izvodjenje;
import java.util.Comparator;

public class ToplistaSorter implements Comparator<Izvodjenje> {
    boolean value;
    public ToplistaSorter(boolean value){
        super();
    }
    @Override
    public int compare(Izvodjenje i1,Izvodjenje i2) {
        int retVal = 0;
        if(value) {
            retVal = i1.getUkupnoPrisupa() - i2.getUkupnoPrisupa();
            return retVal * (-1);
        }else{
            retVal = i1.getBrGlasova() - i2.getBrGlasova();
            return retVal * (-1);
        }
    }
}
