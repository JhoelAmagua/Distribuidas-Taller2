package com.mycompany.practicasistemacentralizado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    
    public static void main(String [] args){
        
        int producto = 1;
        int opcion = 0;
        Test t = new Test();
        
       
        Scanner entrada = new Scanner (System.in);
       
        while(opcion != 5){
            
       
        System.out.println("\nEliga una Opcion (Digite un Numero)");
        System.out.println("1. Agregar Productos");
        System.out.println("2. Actualizar Productos");
        System.out.println("3. Eliminar Productos");
        System.out.println("4. Mostrar Lista de Productos");
        System.out.println("5. Salir\n");
       
        opcion = entrada.nextInt();
        
        switch (opcion) {
            case 1:
                t.mostrar();
                System.out.println("Ingrese el id del producto: ");
                int codigo = entrada.nextInt();
                entrada.nextLine();
                System.out.println("Ingrese el nombre del producto: ");
                String nombre = entrada.nextLine();
                System.out.println("Ingrese el precio del producto: ");
                double precio = entrada.nextDouble();
                t.insertar(codigo,nombre, precio);
                break;
            case 2:
                t.mostrar();
                System.out.println("Ingrese el id del producto que desee modificar: ");
                int newcodigo = entrada.nextInt();
                entrada.nextLine();
                System.out.println("Ingrese el nuevo nombre del producto: ");
                String newnombre = entrada.nextLine();
                System.out.println("Ingrese el nuevo precio del producto: ");
                double newprecio = entrada.nextDouble();
                t.actualizar(newcodigo, newnombre, newprecio);
                break;
            case 3:
                t.mostrar();
                System.out.println("Ingrese el codigo del objeto que desee eliminar: ");
                int deleteid = entrada.nextInt();
                t.eliminar(deleteid);
                
                break;
            case 4:
                t.mostrar();
                break;   
            case 5:
                System.out.println("Adios \n");
                break; 
            default: 
                System.out.println("Opcion no Valida Ingrese otra opcion: ");
                
        }
                
         }
      
    }
    
   
    public Connection getConexion (){
        
    Connection conexion = null;
    String servidor = "localhost";
    String puerto = "5432";    
    String baseDatos = "supermercado";
    String url = "jdbc:postgresql://"+servidor+":"+puerto+"/"+baseDatos;
    String usuario = "postgres";
    String clave = "amxesjd"; 
    
    try{
        conexion = DriverManager.getConnection(url,usuario,clave);
        
    }catch (SQLException ex){
        
        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    }
    return conexion;
    }
    
    public void insertar (int codigo, String nombre, double precio){
        Connection conexion = getConexion();
        String sql = "INSERT INTO producto values("+ codigo + ",'"+ nombre + "','"+ precio + "')";
        
        try(Statement at = conexion.createStatement()){
            at.executeUpdate(sql);
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void actualizar (int codigo, String nombre, double precio){
        Connection conexion = getConexion();
        String sql = "UPDATE producto SET " + "nombre='" +nombre + "'"+ ",precio=" +precio + "WHERE codigo="+ codigo;
        
        try(Statement at = conexion.createStatement()){
            at.executeUpdate(sql);
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void eliminar (int codigo){
        Connection conexion = getConexion();
        String sql = "DELETE FROM producto WHERE codigo = " + codigo;
        
        try(Statement at = conexion.createStatement()){
            at.executeUpdate(sql);
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void mostrar(){
        int contador = 0;
        
        Connection conexion = getConexion();
        String sql = "SELECT codigo, nombre, precio FROM producto ";
        
        try(Statement at = conexion.createStatement()){
             ResultSet datos = at.executeQuery(sql);
             while(datos.next()){
                 contador +=1;
                 int codigo = datos.getInt("codigo");
                 String nombre = datos.getString("nombre");
                 String precio = datos.getString("precio");
                 System.out.println("Producto "+ contador);
                 System.out.print("codigo: "+ codigo +"\t");
                 System.out.print("nombre: "+ nombre +"\t");
                 System.out.println("precio: "+ precio +"\t"+"\n");
                 
             }
            
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

