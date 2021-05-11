PGDMP     )    $        
        y            SmartHomeDB    13.2    13.2 !    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16403    SmartHomeDB    DATABASE     q   CREATE DATABASE "SmartHomeDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Latin America.1252';
    DROP DATABASE "SmartHomeDB";
                postgres    false            �            1259    16493    Admin    TABLE     U   CREATE TABLE public."Admin" (
    email text NOT NULL,
    password text NOT NULL
);
    DROP TABLE public."Admin";
       public         heap    postgres    false            �            1259    16404    Client    TABLE       CREATE TABLE public."Client" (
    name text NOT NULL,
    "primaryLastName" text NOT NULL,
    "secondaryLastName" text NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    continent text NOT NULL,
    country text NOT NULL,
    "deliveryAdresses" text[] NOT NULL
);
    DROP TABLE public."Client";
       public         heap    postgres    false            �            1259    16425    Device    TABLE     ;  CREATE TABLE public."Device" (
    name text NOT NULL,
    "serialNumber" integer NOT NULL,
    "eConsumption" text NOT NULL,
    brand text NOT NULL,
    associated boolean NOT NULL,
    "typeName" text NOT NULL,
    "ownerEmail" text NOT NULL,
    "dniDistributor" integer NOT NULL,
    price integer NOT NULL
);
    DROP TABLE public."Device";
       public         heap    postgres    false            �            1259    16412 
   DeviceType    TABLE     �   CREATE TABLE public."DeviceType" (
    name text NOT NULL,
    description text NOT NULL,
    "warrantyTime" integer NOT NULL
);
     DROP TABLE public."DeviceType";
       public         heap    postgres    false            �            1259    16451    Device_serialNumber_seq    SEQUENCE     �   ALTER TABLE public."Device" ALTER COLUMN "serialNumber" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Device_serialNumber_seq"
    START WITH 1199
    INCREMENT BY 27
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    202            �            1259    16433    Distributor    TABLE     �   CREATE TABLE public."Distributor" (
    dni integer NOT NULL,
    name text NOT NULL,
    continent text NOT NULL,
    country text NOT NULL
);
 !   DROP TABLE public."Distributor";
       public         heap    postgres    false            �            1259    16461    Invoice    TABLE     �   CREATE TABLE public."Invoice" (
    "invoiceNumber" integer NOT NULL,
    "deviceSerialNumber" integer NOT NULL,
    price integer NOT NULL,
    date text NOT NULL
);
    DROP TABLE public."Invoice";
       public         heap    postgres    false            �            1259    16453    Order    TABLE        CREATE TABLE public."Order" (
    "consecutiveNumberOrder" integer NOT NULL,
    "deviceSerialNumber" integer NOT NULL,
    date text NOT NULL,
    hour text NOT NULL,
    brand text NOT NULL,
    price integer NOT NULL,
    "deviceOwner" text NOT NULL,
    "orderID" integer NOT NULL
);
    DROP TABLE public."Order";
       public         heap    postgres    false            �          0    16493    Admin 
   TABLE DATA           2   COPY public."Admin" (email, password) FROM stdin;
    public          postgres    false    207   �'       �          0    16404    Client 
   TABLE DATA           �   COPY public."Client" (name, "primaryLastName", "secondaryLastName", email, password, continent, country, "deliveryAdresses") FROM stdin;
    public          postgres    false    200   �'       �          0    16425    Device 
   TABLE DATA           �   COPY public."Device" (name, "serialNumber", "eConsumption", brand, associated, "typeName", "ownerEmail", "dniDistributor", price) FROM stdin;
    public          postgres    false    202   ~(       �          0    16412 
   DeviceType 
   TABLE DATA           I   COPY public."DeviceType" (name, description, "warrantyTime") FROM stdin;
    public          postgres    false    201   �(       �          0    16433    Distributor 
   TABLE DATA           F   COPY public."Distributor" (dni, name, continent, country) FROM stdin;
    public          postgres    false    203   �(       �          0    16461    Invoice 
   TABLE DATA           W   COPY public."Invoice" ("invoiceNumber", "deviceSerialNumber", price, date) FROM stdin;
    public          postgres    false    206   �(       �          0    16453    Order 
   TABLE DATA           �   COPY public."Order" ("consecutiveNumberOrder", "deviceSerialNumber", date, hour, brand, price, "deviceOwner", "orderID") FROM stdin;
    public          postgres    false    205   )       �           0    0    Device_serialNumber_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public."Device_serialNumber_seq"', 1361, true);
          public          postgres    false    204            N           2606    16500    Admin Admin_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public."Admin"
    ADD CONSTRAINT "Admin_pkey" PRIMARY KEY (email);
 >   ALTER TABLE ONLY public."Admin" DROP CONSTRAINT "Admin_pkey";
       public            postgres    false    207            B           2606    16411    Client Client_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public."Client"
    ADD CONSTRAINT "Client_pkey" PRIMARY KEY (email);
 @   ALTER TABLE ONLY public."Client" DROP CONSTRAINT "Client_pkey";
       public            postgres    false    200            D           2606    16419    DeviceType DeviceType_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public."DeviceType"
    ADD CONSTRAINT "DeviceType_pkey" PRIMARY KEY (name);
 H   ALTER TABLE ONLY public."DeviceType" DROP CONSTRAINT "DeviceType_pkey";
       public            postgres    false    201            F           2606    16432    Device Device_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."Device"
    ADD CONSTRAINT "Device_pkey" PRIMARY KEY ("serialNumber");
 @   ALTER TABLE ONLY public."Device" DROP CONSTRAINT "Device_pkey";
       public            postgres    false    202            H           2606    16440    Distributor Distributor_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public."Distributor"
    ADD CONSTRAINT "Distributor_pkey" PRIMARY KEY (dni);
 J   ALTER TABLE ONLY public."Distributor" DROP CONSTRAINT "Distributor_pkey";
       public            postgres    false    203            L           2606    16468    Invoice Invoice_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public."Invoice"
    ADD CONSTRAINT "Invoice_pkey" PRIMARY KEY ("invoiceNumber");
 B   ALTER TABLE ONLY public."Invoice" DROP CONSTRAINT "Invoice_pkey";
       public            postgres    false    206            J           2606    16477    Order Order_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_pkey" PRIMARY KEY ("orderID");
 >   ALTER TABLE ONLY public."Order" DROP CONSTRAINT "Order_pkey";
       public            postgres    false    205            R           2606    16483    Order deviceOwner    FK CONSTRAINT     �   ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "deviceOwner" FOREIGN KEY ("deviceOwner") REFERENCES public."Client"(email) NOT VALID;
 ?   ALTER TABLE ONLY public."Order" DROP CONSTRAINT "deviceOwner";
       public          postgres    false    2882    200    205            S           2606    16469    Invoice deviceSerialNumber    FK CONSTRAINT     �   ALTER TABLE ONLY public."Invoice"
    ADD CONSTRAINT "deviceSerialNumber" FOREIGN KEY ("deviceSerialNumber") REFERENCES public."Device"("serialNumber");
 H   ALTER TABLE ONLY public."Invoice" DROP CONSTRAINT "deviceSerialNumber";
       public          postgres    false    206    2886    202            Q           2606    16478    Order deviceSerialNumber    FK CONSTRAINT     �   ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "deviceSerialNumber" FOREIGN KEY ("deviceSerialNumber") REFERENCES public."Device"("serialNumber") NOT VALID;
 F   ALTER TABLE ONLY public."Order" DROP CONSTRAINT "deviceSerialNumber";
       public          postgres    false    2886    202    205            P           2606    16446    Device dniDistributor    FK CONSTRAINT     �   ALTER TABLE ONLY public."Device"
    ADD CONSTRAINT "dniDistributor" FOREIGN KEY ("dniDistributor") REFERENCES public."Distributor"(dni) NOT VALID;
 C   ALTER TABLE ONLY public."Device" DROP CONSTRAINT "dniDistributor";
       public          postgres    false    202    2888    203            T           2606    16488    Invoice invoiceNumber    FK CONSTRAINT     �   ALTER TABLE ONLY public."Invoice"
    ADD CONSTRAINT "invoiceNumber" FOREIGN KEY ("invoiceNumber") REFERENCES public."Order"("orderID") NOT VALID;
 C   ALTER TABLE ONLY public."Invoice" DROP CONSTRAINT "invoiceNumber";
       public          postgres    false    205    206    2890            O           2606    16441    Device typeName    FK CONSTRAINT     �   ALTER TABLE ONLY public."Device"
    ADD CONSTRAINT "typeName" FOREIGN KEY ("typeName") REFERENCES public."DeviceType"(name) NOT VALID;
 =   ALTER TABLE ONLY public."Device" DROP CONSTRAINT "typeName";
       public          postgres    false    2884    202    201            �      x������ � �      �   �   x�e�K
�0E�/�(]@���"~�I�:���>hr5MT�w�3qv.�{�m�UJ���rFxdM�0�M��}e�e�\�e�]T�9$�_�7��d����7��O�����?�ˑ��ZyA(N\9�ke�y06�      �      x������ � �      �      x����K��, Q
���\1z\\\ V�      �   (   x�325����KO�t�M-�LN�t�/.IT2�b���� �!	�      �      x������ � �      �      x������ � �     