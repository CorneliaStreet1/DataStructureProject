package HYH.Hoffman;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Hoffman {
    class byteCode implements Comparable{
        private byteCode leftTree,rightTree;
        private int times;
        private byte b;
        private String hoffCode;

        public byteCode(){
            clear();
        }
        public void clear(){
            leftTree=rightTree=null;
            times=0;
            b=0;
            hoffCode=null;
        }
        //写方法
        public void add_sons(byteCode leftTree,byteCode rightTree){
            this.leftTree=leftTree;
            this.rightTree=rightTree;
            this.times=leftTree.times+ rightTree.times;
        }
        public void addTimes(){
            times++;
        }
        public void writeHoffCode(String hoffCode){
            this.hoffCode=hoffCode;
        }
        public void writeB(byte b){
            this.b=b;
        }
        public void writeTimes(int times){
            this.times=times;
        }
        //读方法
        public int getTimes(){
            return times;
        }
        public byteCode getLeftTree(){
            return leftTree;
        }
        public byteCode getRightTree(){
            return rightTree;
        }
        public String getHoffCode(){
            return hoffCode;
        }
        public byte getB(){
            return b;
        }

        public int compareTo(Object o) {//优先队列到底怎么个优先，大小法的？
            byteCode other=(byteCode) o;
            if(times>other.times) return 1;
            else if(times==other.times) return 0;
            else return -1;
        }
    }

    public Hoffman(){
        for(int i=0;i<codeNum;++i){
            byteCodes[i]=new byteCode();
        }
    }


    //根节点
    private byteCode root;
    //原字节数，压缩后字节数
    private long total_byte, compress_byte;
    //字节总数
    private final int codeNum = 256;
    //节点数组，霍夫曼树就在这的基础上建立
    private byteCode[] byteCodes = new byteCode[codeNum];
    //压缩文件后缀名
    private final String suffix_h = ".hoff";
    //解压后的名字，带扩展名
    private String decompress_name;
    //操作的文件，产生的文件
    private File operate_file, result_file;
    //若干读写流
    private BufferedInputStream inputStream=null;
    private BufferedOutputStream outputStream=null;
    private DataInputStream dataInput=null;
    private DataOutputStream dataOutput=null;



    private void byteCodes_clear(){
        for(int i=0;i<codeNum;++i){
            byteCodes[i].clear();
            byteCodes[i].writeB((byte) i);
        }
    }
    private void init(){
        root=null;
        decompress_name=null;
        operate_file=result_file=null;
        byteCodes_clear();
        total_byte=compress_byte=0;
    }

    //创建产生的文件
    private void createResultFile(String toPath,String name,String suf){
        result_file=new File(toPath+"\\"+name+suf);
        if(result_file.exists()){
            boolean runkey=true;
            int i=1;
            while(runkey){
                result_file=new File(toPath+"\\"+name+"("+i+")"+suf);
                if(!result_file.exists()) runkey=false;
                ++i;
            }
        }
        try {
            result_file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建压缩文件失败");
        }
    }

    //统计次数
    private void countTimes(){
        System.out.println("统计次数中");
        long start=System.currentTimeMillis();
        try {
            BufferedInputStream bufferIn=new BufferedInputStream(new FileInputStream(operate_file));
//            DataInputStream dataIn=new DataInputStream(bufferIn);
            int temp;
            while((temp=bufferIn.read())!=-1){
                byteCodes[temp].addTimes();
                total_byte++;
            }
            bufferIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件输入流失败");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("输入流出错");
        }
        long stop=System.currentTimeMillis();
        stop-=start;
        System.out.println("统计完成，耗时"+stop+"ms");
    }
    //建立哈夫曼树
    private void createHoffmanTree(){
        long start=System.currentTimeMillis();
        System.out.println("建霍夫曼树");
        PriorityQueue<byteCode> Pqueue=new PriorityQueue<>();
        for(int i=0;i<codeNum;++i) if(byteCodes[i].getTimes()>0) Pqueue.add(byteCodes[i]);
        while(Pqueue.size()>1){
            byteCode left,right,temp;
            temp=new byteCode();
            left=Pqueue.poll();
            right=Pqueue.poll();
            temp.add_sons(left,right);
            Pqueue.add(temp);
        }
        root=Pqueue.poll();
        long stop=System.currentTimeMillis();
        stop-=start;
        System.out.println("耗时："+stop+"ms");
    }
    //获得哈夫曼编码
    private void recursionMap(byteCode thisByteCode,StringBuilder hoff){
        byteCode left,right;
        left=thisByteCode.getLeftTree();
        right=thisByteCode.getRightTree();
        if(left==null){
            String hoffCode=hoff.toString();
//            int temp=thisByteCode.getTimes();
//            char c= (char) thisByteCode.getB();
//            System.out.println(c+":"+hoffCode+":"+temp);
            thisByteCode.writeHoffCode(hoffCode);
            return;
        }
        hoff.append("0");
        recursionMap(left,hoff);
        hoff.delete(hoff.length()-1,hoff.length());
        hoff.append("1");
        recursionMap(right,hoff);
        hoff.delete(hoff.length()-1,hoff.length());
    }
    private void createByteMapHoffman(){
        long start=System.currentTimeMillis();
        System.out.println("得到原字节和霍夫曼编码的映射");
        StringBuilder hoff=new StringBuilder();
        recursionMap(root,hoff);
        long stop=System.currentTimeMillis();
        stop-=start;
        System.out.println("耗时："+stop+"ms");
    }//这个就是写每个节点对应的String 哈夫曼编码

    //输出压缩文件
    private void outputCompressCode(){
        long start=System.currentTimeMillis();
        System.out.println("输出压缩文件中……");
        try {
            inputStream=new BufferedInputStream(new FileInputStream(operate_file));

            outputStream=new BufferedOutputStream(new FileOutputStream(result_file));
            dataOutput=new DataOutputStream(outputStream);


            dataOutput.writeLong(total_byte);
            dataOutput.writeUTF(decompress_name);
            for(int i=0;i<256;++i){ dataOutput.writeInt(byteCodes[i].getTimes()); }

            //缓冲字节
            int empty_num=8;
            byte buffer_byte=0;

            //用于找到对应的字节类
            int temp_byte;
            //循环读入
            while((temp_byte=inputStream.read())!=-1){
                if(temp_byte<0) temp_byte+=256;
                String temp_hoff=byteCodes[temp_byte].getHoffCode();
                int begin=0;
                int now_len=temp_hoff.length();

                //将字符串完全转化成字节
                while(now_len>0){
                    int subLen=now_len>empty_num?empty_num:now_len;
                    now_len-=subLen;
                    /*
                    byte add_byte=(byte) Integer.parseInt(temp_hoff.substring(begin,begin+subLen),2);
                    //0开头的字符串前面的0没有处理，begin也要往后退
                    //0开头不用处理，左移会包容它的
                    */
                    byte add_byte=(byte) Integer.parseInt(temp_hoff.substring(begin,begin+subLen),2);
                    begin+=subLen;
                    empty_num-=subLen;
                    add_byte=(byte)(add_byte<<empty_num);
                    buffer_byte|=add_byte;
                    if(empty_num==0){
                        dataOutput.write(buffer_byte);
//                        System.out.println(buffer_byte);
                        empty_num=8;
                        buffer_byte=0;

                        compress_byte++;
                    }
                }
            }
            if(empty_num!=8) dataOutput.write(buffer_byte);
            inputStream.close();
            dataOutput.close();
            outputStream.close();
            System.out.println("输出压缩文件完成");
            long stop=System.currentTimeMillis();
            stop-=start;
            System.out.println("耗时："+stop+"ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读入压缩文件初始化
    private void readInit(){
        long start=System.currentTimeMillis();
        try {
            System.out.println("解压初始化中……");
            inputStream=new BufferedInputStream(new FileInputStream(operate_file));
            dataInput=new DataInputStream(inputStream);

            total_byte=dataInput.readLong();
            decompress_name=dataInput.readUTF();
            for(int i=0;i<256;i++){ byteCodes[i].writeTimes(dataInput.readInt());}

            System.out.println("解压初始化完成");

            long stop=System.currentTimeMillis();
            stop-=start;
            System.out.println("耗时："+stop+"ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //输出解压文件
    private void outputDecompressCode(){
        long start=System.currentTimeMillis();
        System.out.println("解压中……");
        long need_decompress_num=total_byte;
        byteCode nowByte=root;
        try {
            outputStream=new BufferedOutputStream(new FileOutputStream(result_file));

            byte buffer_byte;
            int temp;

            while((temp=inputStream.read())!=-1){//返回的就是int，但int的-1和byte的不同
                buffer_byte=(byte) temp;
                int cycle_num=8;

                if(need_decompress_num==total_byte-3){
                    int i=0;//就用来看断点的
                }

                while(need_decompress_num>0 && cycle_num>0){
                    int first=buffer_byte<0?1:0;//模mod负数会得负数，但后面判断是否为0，为什么会有影响呢？
                    buffer_byte=(byte) (buffer_byte<<1);
                    cycle_num--;
                    byteCode left,right;
                    left=nowByte.getLeftTree();
                    right=nowByte.getRightTree();
                    nowByte=first==0?left:right;
                    if(nowByte.getLeftTree()==null){
                        outputStream.write(nowByte.getB());
                        need_decompress_num--;
                        nowByte=root;
                    }
                }
            }
            outputStream.close();
            dataInput.close();
            inputStream.close();

            System.out.println("解压完成");

            long stop=System.currentTimeMillis();
            stop-=start;
            System.out.println("耗时："+stop+"ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //对外提供的压缩功能
    public void compress(File need_compress,String toPath){
        init();
        operate_file=need_compress;
        decompress_name=operate_file.getName();

        String name=decompress_name.substring(0,decompress_name.lastIndexOf("."));
        createResultFile(toPath,name,suffix_h);

        countTimes();
        createHoffmanTree();
        createByteMapHoffman();
        outputCompressCode();//注意写入压缩前字节总数等等
    }

    //对外提供的解压功能
    public void decompress(File need_decompress,String toPath){
        init();
        operate_file=need_decompress;
        readInit();//初始化了输入流dataInput

        String name=decompress_name.substring(0,decompress_name.lastIndexOf("."));
        String suffix=decompress_name.substring(
                decompress_name.lastIndexOf("."),
                decompress_name.length());
        createResultFile(toPath,name,suffix);

        createHoffmanTree();
        outputDecompressCode();//注意解压读入的字节可能要去掉结尾那几个比特
    }
}
