using BackEndData;
using BackEndData.InterfaceRepositories;
using BackEndData.Repositories;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.OpenApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd
{
    /// <summary>
    /// Clase para configurar el start del web api
    /// </summary>
    public class Startup
    {
        /// <summary>
        /// constructor de la clase
        /// </summary>
        /// <param name="configuration">string que indica la configuracion</param>
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {

            services.AddCors(options => options.AddDefaultPolicy(
                builder => builder.AllowAnyOrigin()));

            var postgreSQLConnectionConfiguration = new PostgreSQLConfiguration(Configuration.GetConnectionString("PostgreSQLConnection"));

            services.AddSingleton(postgreSQLConnectionConfiguration);

            services.AddScoped<IClient, ClientR>();
            services.AddScoped<IDeviceType, DeviceTypeR>();
            services.AddScoped<IDevice, DeviceR>();
            services.AddScoped<IAdmin, AdminR>();
            services.AddScoped<IOrder, OrderR>();
            services.AddScoped<IDistributor, DistributorR>();
            services.AddScoped<IInvoice, InvoiceR>();
            services.AddScoped<IRoom, RoomR>();
            services.AddScoped<IAppDevice, AppDeviceR>();
            services.AddScoped<IControl, ControlR>();
            services.AddControllers();

            

            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { Title = "PostgreSQLBackEnd", Version = "v1" });
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseSwagger();
                app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "PostgreSQLBackEnd v1"));
            }

            app.UseCors(builder =>
                builder.WithOrigins().AllowAnyHeader().AllowAnyMethod().AllowAnyOrigin());

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
