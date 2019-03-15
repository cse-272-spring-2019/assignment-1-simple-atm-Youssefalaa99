public class History {
    private String[]array=new String[5];
    private int index=0;
    private int historyIndex;


    public void addString(String text){
        if(index>=0 && index<=4)
        {
            array[index]=text;
            historyIndex=index;
            index++;

        }
        else
        {
            int i;
            for(i=0;i<4;i++)
            {
                array[i]=array[i+1];
            }
            array[i]=text;
            historyIndex =i;
        }
    }

    public void Next() {
        historyIndex++;
        if(historyIndex>4)
            historyIndex=4;
    }


    public void Prev() {
        historyIndex--;
        if(historyIndex<0)
            historyIndex=0;
    }

    public String getString(){

        if(array[historyIndex]==null)
            return null;
        else return array[historyIndex];
    }

}
