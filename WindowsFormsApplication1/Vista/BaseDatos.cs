using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Npgsql;

namespace WindowsFormsApplication1.Vista
{
    class BaseDatos
    {
        public NpgsqlConnection abrirConexion()
        {
            NpgsqlConnection conexion = new NpgsqlConnection();
            var cadenaConexion = "Server = localhost; Port = 5400; User Id = postgres; Password = ocaso; Database = postgres; ";

            try
            {
                conexion = new NpgsqlConnection(cadenaConexion);
                conexion.Open();
                System.Console.WriteLine("Conexion Exitosa");
            }
            catch(Exception)
            {
                System.Console.WriteLine("Conexion Fallida");
                conexion.Close();
            }
            

            return conexion;
        }
    }
}
