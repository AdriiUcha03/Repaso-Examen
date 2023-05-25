class Empleado{
    String nombre;
    String apellido;
    int salario;
    boolean sabeingles;
    boolean activo = true;
    public Empleado(String nombre,String apellido,int salario,boolean sabeingles){
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.sabeingles = sabeingles;
    }

    void subesueldo(int cantidad){
        this.salario =+ cantidad;
        System.out.println("Se le a subido el salario a " + this.nombre);
    }

    void despedir(){
        this.activo = false;
        System.out.println("El empleado " + this.nombre + " ha sido despedido");
    }
}

class Administrativo extends Empleado{

    boolean titulado = false;

    int numfacturas;

    public Administrativo(String nombre, String apellido, int salario, boolean sabeingles,int numfacturas) {
        super(nombre, apellido, salario,sabeingles);
        this.numfacturas = numfacturas;
    }

    void consiguetitutlo(){
        this.titulado = true;
        System.out.println("Empleado pasa a ser titulado");
    }
}

class Tecnico extends Empleado {
    int numreapraciones;

    boolean sabejava = false;

    public Tecnico(String nombre, String apellido, int salario, boolean sabeingles, int numreapraciones) {
        super(nombre, apellido, salario, sabeingles);
        this.numreapraciones = numreapraciones;
    }

    void aprendejava(){
        this.sabejava = true;
        System.out.println("El empleado se ha titulado en java");
    }
}
public class EjercicioExamen {
    public static void main(String[] args) {
        Administrativo administrativo1 = new Administrativo("Ricky","Ulloa",1500,true,250);
        Administrativo administrativo2 = new Administrativo("David","Picazo",1500,false,120);
        Tecnico tecnico1 = new Tecnico("Adrian","Ucha",1080,false,50);
        Tecnico tecnico2 = new Tecnico("David","Nuñez",1600,true,40);


        Empleado [] listaempleados = new Empleado[4];

        administrativo1.subesueldo(500);
        administrativo2.despedir();
        administrativo1.consiguetitutlo();
        tecnico1.aprendejava();

        listaempleados[0] = administrativo1;
        listaempleados[1] = administrativo2;
        listaempleados[2] = tecnico1;
        listaempleados[3] = tecnico2;

        for (int i= 0;i < 4;i++){
            if (listaempleados[i].sabeingles == true){
                listaempleados[i].subesueldo(100);
            }
        }

        for (int i= 0;i < 4;i++){
            if (listaempleados[i] instanceof Administrativo){
                if (((Administrativo)listaempleados[i]).titulado == false){
                    ((Administrativo)listaempleados[i]).despedir();
                }
            }

            if (listaempleados[i] instanceof Administrativo) {
                if (((Administrativo) listaempleados[i]).numfacturas < 250) {
                    ((Administrativo) listaempleados[i]).despedir();
                }
            }
        }
    }
}
