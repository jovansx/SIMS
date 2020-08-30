package gui.sorters;
import model.Izvodjenje;
import java.util.Comparator;

public class ToplistaSorter implements Comparator<Izvodjenje> {

    public ToplistaSorter(){
        super();
    }
    @Override
    public int compare(Izvodjenje i1,Izvodjenje i2) {
        int retVal = 0;
        retVal = i1.getUkupnoPrisupa()-i2.getUkupnoPrisupa();
        return retVal*(-1);
    }
}
