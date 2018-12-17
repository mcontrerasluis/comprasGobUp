/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrdenCompraComponentsPage, OrdenCompraDeleteDialog, OrdenCompraUpdatePage } from './orden-compra.page-object';

const expect = chai.expect;

describe('OrdenCompra e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let ordenCompraUpdatePage: OrdenCompraUpdatePage;
    let ordenCompraComponentsPage: OrdenCompraComponentsPage;
    let ordenCompraDeleteDialog: OrdenCompraDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load OrdenCompras', async () => {
        await navBarPage.goToEntity('orden-compra');
        ordenCompraComponentsPage = new OrdenCompraComponentsPage();
        expect(await ordenCompraComponentsPage.getTitle()).to.eq('comprasGobUpApp.ordenCompra.home.title');
    });

    it('should load create OrdenCompra page', async () => {
        await ordenCompraComponentsPage.clickOnCreateButton();
        ordenCompraUpdatePage = new OrdenCompraUpdatePage();
        expect(await ordenCompraUpdatePage.getPageTitle()).to.eq('comprasGobUpApp.ordenCompra.home.createOrEditLabel');
        await ordenCompraUpdatePage.cancel();
    });

    it('should create and save OrdenCompras', async () => {
        const nbButtonsBeforeCreate = await ordenCompraComponentsPage.countDeleteButtons();

        await ordenCompraComponentsPage.clickOnCreateButton();
        await promise.all([
            ordenCompraUpdatePage.setFechaEntradaInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            ordenCompraUpdatePage.estatusSelectLastOption(),
            ordenCompraUpdatePage.setCodigoInput('codigo'),
            ordenCompraUpdatePage.setLugarEntregaDInput('lugarEntregaD'),
            ordenCompraUpdatePage.lugarEntregaSelectLastOption(),
            ordenCompraUpdatePage.dependenciaSelectLastOption()
        ]);
        expect(await ordenCompraUpdatePage.getFechaEntradaInput()).to.contain('2001-01-01T02:30');
        expect(await ordenCompraUpdatePage.getCodigoInput()).to.eq('codigo');
        expect(await ordenCompraUpdatePage.getLugarEntregaDInput()).to.eq('lugarEntregaD');
        await ordenCompraUpdatePage.save();
        expect(await ordenCompraUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await ordenCompraComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last OrdenCompra', async () => {
        const nbButtonsBeforeDelete = await ordenCompraComponentsPage.countDeleteButtons();
        await ordenCompraComponentsPage.clickOnLastDeleteButton();

        ordenCompraDeleteDialog = new OrdenCompraDeleteDialog();
        expect(await ordenCompraDeleteDialog.getDialogTitle()).to.eq('comprasGobUpApp.ordenCompra.delete.question');
        await ordenCompraDeleteDialog.clickOnConfirmButton();

        expect(await ordenCompraComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
