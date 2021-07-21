package hw_02_01;

public interface RunAndJumpable {
    String getName();
    int getRunLimit();
    int getJumpLimit();

    default boolean run(int value){
        int limit=getRunLimit();
        boolean r=value<=limit;
        if (r){
            System.out.println(getName()+" пробежал "+value+" метров");
        } else {
            System.out.println(getName()+" не смог пробежать "+value+" метров");
        }
        return r;
    }

    default boolean jump(int value){
        int limit=getJumpLimit();
        boolean r=value<=limit;
        if (r){
            System.out.println(getName()+" подпрыгнул на "+value+" метров");
        } else {
            System.out.println(getName()+" не смог подпрыгнуть на "+value+" метров");
        }
        return r;
    }
}
