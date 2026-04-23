import type { Metadata } from "next";
import "./globals.css";
import Navbar from "./_components/_navigation/navbar";
import Header from "./_components/_navigation/header";
import Logo from "./_components/_navigation/logo";


export const metadata: Metadata = {
  title: "Calculia Analytics",
  description: "A tool modified for IRT analytics",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
      >
        <div className="min-h-screen">
          <Header />
          <Navbar />
          <Logo />
          <main className="container mx-auto px-4 py-8"> {children}</main>
         

        </div>
      </body>
    </html>
  );
}
