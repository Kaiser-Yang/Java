class dog {
    String name;
    public static void main(String[] args){
        dog dog1 = new dog();
        dog1.name = "Bart";
        dog[] myDogs = new dog[3];
        for (int i = 0;i < 2;i ++) myDogs[i] = new dog();
        myDogs[2] = myDogs[1];

        myDogs[0].name = "Fred";
        myDogs[1].name = "Marge";

        System.out.println("the last dog's name is " + myDogs[2].name);

        int x = 0;
        while(x < myDogs.length){
            myDogs[x].bark();
            x ++;
        }
    }
    public void bark(){
        System.out.println(name + " says Ruff!");
    }
}
